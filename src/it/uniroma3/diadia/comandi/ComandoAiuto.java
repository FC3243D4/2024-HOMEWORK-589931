package it.uniroma3.diadia.comandi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.net.URL;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	public ComandoAiuto(IO io) {
		super("aiuto", io);
	}

	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private static Class[] getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void esegui(Partita partita) throws ClassNotFoundException, IOException {
		//		for(ElencoComandi comando : ElencoComandi.values())
		//			this.getIo().mostraMessaggio(comando+" ");
		//		this.getIo().mostraMessaggio("");

		String nomeClasse = "it.uniroma3.diadia.comandi";
		Class[] classi = getClasses(nomeClasse);
		StringBuilder s = null;
		StringBuilder out = new StringBuilder();
		boolean first=true;
		for(Class c : classi) {
			if(c.getSimpleName().contains("Comando") && !(c.getSimpleName().equals("Comando") || c.getSimpleName().equals("AbstractComando"))
					&& !c.getSimpleName().contains("Test") && !c.getSimpleName().contains("NonValido")) {
				s= new StringBuilder(c.getSimpleName().toLowerCase());
				if(first==true) {
					out.append(s.substring(7, s.length()));
					first=false;
				}
				else out.append(", "+s.substring(7, s.length()));
			}
		}
		this.getIo().mostraMessaggio(out.toString());
	}
}
