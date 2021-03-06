package com.example.barclays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemOne {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		ConveyorSystem conveyorSystem = new ConveyorSystem();
		Map<String, Departure> departures = new HashMap<String, Departure>();
		List<Luggage> luggages = new ArrayList<Luggage>();

		// BufferedReader br = new BufferedReader(new FileReader(new File("C:/","input.txt")));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = 0;
		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.contains("#")) {
				c++;
				continue;
			}

			String input[] = line.split(" ");

			if (c == 1) {
				conveyorSystem.buildConveyorPath(input[0], input[1], Integer.parseInt(input[2]));
			}

			if (c == 2) {
				Departure departure = new Departure(input[0], input[1], input[2], input[3]);
				departures.put(input[0], departure);
			}

			if (c == 3) {
				Luggage luggage = new Luggage(input[0], input[1], input[2]);

				if (!input[2].equalsIgnoreCase("ARRIVAL")) {
					Departure departure = departures.get(input[2]);
					departure.addBags(luggage);
				}

				luggages.add(luggage);
			}

			if (line.contains(new String("ARRIVAL"))) {
				break;
			}
		}

		br.close();

		for (Luggage luggage : luggages) {
			String end = null;

			if (luggage.getFlightId().equalsIgnoreCase("ARRIVAL")) {
				end = "BaggageClaim";
			} else {
				Departure departure = departures.get(luggage.getFlightId());
				end = departure.getFlightGate();
			}

			Double totalTime = conveyorSystem.findPath(luggage.getEntryPoint(), end);

			System.out.println(luggage.getBagNumber() + " " + conveyorSystem.showPath(end) + " : " + totalTime.intValue());

			Map<String, Point> points = conveyorSystem.getPoints();
			Collection<Point> values = points.values();

			for (Point point : values) {
				point.setMinimumDistance(Double.POSITIVE_INFINITY);
				point.setPrev(null);
			}
		}
	}
}
