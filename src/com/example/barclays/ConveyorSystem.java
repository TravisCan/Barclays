package com.example.barclays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConveyorSystem {
	private Map<String, Point> points = new HashMap<String, Point>();

	public Map<String, Point> getPoints() {
		return points;
	}

	public void setPoints(Map<String, Point> points) {
		this.points = points;
	}

	public void buildConveyorPath(String s, String d, int t) {
		Point dest = null;
		Point source = null;

		if (!this.points.containsKey(s)) {
			source = new Point(s);
			this.points.put(s, source);
		} else {
			source = this.points.get(s);
		}

		if (!this.points.containsKey(d)) {
			dest = new Point(d);
			this.points.put(d, dest);
		} else {
			dest = this.points.get(d);
		}

		Path sp = createDestination(dest, t);
		Path dp = createDestination(source, t);

		source.addDestination(sp);
		dest.addDestination(dp);
	}

	private Path createDestination(Point source, int t) {
		return new Path(source, t);
	}

	public double findPath(String start, String end) {
		Point point = this.points.get(start);

		Queue<Point> queue = new PriorityQueue<Point>();
		queue.add(point);

		point.setMinimumDistance(0);

		while (!queue.isEmpty()) {
			Point n = queue.poll();

			List<Path> destinations = n.getDestinations();
			for (Path path : destinations) {
				Point targetPoint = path.getTargetPoint();
				int travelTime = path.getTravelTime();

				double distance = n.getMinimumDistance() + travelTime;

				if (distance < targetPoint.getMinimumDistance()) {
					targetPoint.setMinimumDistance(distance);
					targetPoint.setPrev(n);

					queue.add(targetPoint);
				}
			}
		}

		Point point2 = this.points.get(end);

		if (point2 != null) {
			return point2.getMinimumDistance();
		} else {
			return 0;
		}
	}

	public String showPath(String targetNode) {
		Point point = this.points.get(targetNode);

		StringBuilder sb = new StringBuilder();

		for (Point n = point; n != null; n = n.getPrev()) {
			sb.insert(0, " " + n.getName());
		}

		sb.delete(0, 1);

		return sb.toString();
	}
}
