package com.clainchoupi.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.clainchoupi.model.Client;
import com.clainchoupi.model.Clients;
import com.clainchoupi.model.Result;

public class Simulator {
	private File file;
    private Clients clients = new Clients();
	private Result result = new Result();
	private LinkedHashMap<String, Long> sortedLikes = new LinkedHashMap<>();
	private LinkedHashMap<String, Long> sortedDislikes = new LinkedHashMap<>();
	private Map<String, Long> collectLiked;
	private Map<String, Long> collectDisliked;


	public Simulator (File file) {
		this.file = file;
	}

	public void parseInput () {
		int bufferSize = 8 * 1024;
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()), bufferSize);
			String line = bufferedReader.readLine();

			//Lire ligne 1
            Integer nbClients = Integer.parseInt(line);
            clients.setClientCount(nbClients);

			//Par blocs de deux lignes, ajouter chaque client
            for (int i = 0; i < nbClients; i++) {
                Client client = new Client();

				//Lecture ligne suivante : Like
                String lineLikes = bufferedReader.readLine();
				String[] likes = lineLikes.split(" ");
				client.setNbLikes(Integer.parseInt(likes[0]));
				likes = Arrays.copyOfRange(likes, 1, likes.length);
                client.setLikes(likes);
				//Ajoute uniquement un liked s'il n'est pas présent dans le HashSet
				result.getAllLiked().addAll(Arrays.asList(likes));
				//Ajoute tous les liked pour faire un count ensuite
				result.getListLiked().addAll(Arrays.asList(likes));

				//Lecture ligne suivante : Dislike
                String lineDislikes = bufferedReader.readLine();
				String[] dislikes = lineDislikes.split(" ");
				client.setNbDislikes(Integer.parseInt(dislikes[0]));
				dislikes = Arrays.copyOfRange(dislikes, 1, dislikes.length);
                client.setDisikes(dislikes);
				//Ajoute uniquement un disliked s'il n'est pas présent dans le HashSet
				result.getAllDisliked().addAll(Arrays.asList(dislikes));

				//Ajoute tous les disliked pour faire un count ensuite
				result.getListDisliked().addAll(Arrays.asList(dislikes));

				//Ajout du client en cours
				clients.addClient(client);
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void simulate() {
		//V6 : mettre tous les liked et enlever les ingrédients plus dislikés que likés

		//Dans tous les cas on ajoute tous les likes
		Iterator<String> it = result.getAllLiked().iterator();
		while (it.hasNext()) {
			String ingredient = (String) it.next();
			result.getIngredients().add(ingredient);
		}

		// Map des disliked groupée par nombre
		collectLiked = result.getListLiked().stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		collectDisliked = result.getListDisliked().stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		
		collectLiked.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedLikes.put(x.getKey(), x.getValue()));

        collectDisliked.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedDislikes.put(x.getKey(), x.getValue()));

		//On enlève les ingrédients plus dislikés que likés
		// Parcours des disliked groupés par nombre
		Iterator<String> itDisliked = collectDisliked.keySet().iterator();
		while(itDisliked.hasNext()) {
			String ingredient = (String) itDisliked.next();
			if (collectLiked.get(ingredient) != null) {
				if (collectLiked.get(ingredient) < collectDisliked.get(ingredient)) {
					result.getIngredients().remove(ingredient);
				}
			}
		}
	}

	public void printOutput () {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/main/resources/results/"+file.getName() + ".out", "UTF-8");
			String resultString = "";

			resultString += result.getIngredients().size();

			for (String ingredient : result.getIngredients()) {
				resultString += " " + ingredient;
			}
			writer.print(resultString);
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void printStats () {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/main/resources/stats/"+file.getName() + ".stats", "UTF-8");
			writer.println("Distinct liked : " +result.getAllLiked());
			writer.println("Sorted Liked : " +sortedLikes);
			writer.println("---------------------------------");
			writer.println("Distinct Disliked : " +result.getAllDisliked());
			writer.println("Sorted Disliked : " +sortedDislikes);

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
