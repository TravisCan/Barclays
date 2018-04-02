package com.example.barclays;

public class Path {
	private Point targetPoint;

	private int travelTime;

	public Path(Point targetPoint, int travelTime) {
		this.targetPoint = targetPoint;
		this.travelTime = travelTime;
	}

	public Point getTargetPoint() {
		return targetPoint;
	}

	public void setTargetPoint(Point targetPoint) {
		this.targetPoint = targetPoint;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((targetPoint == null) ? 0 : targetPoint.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Path other = (Path) obj;
		if (targetPoint == null) {
			if (other.targetPoint != null) {
				return false;
			}
		} else if (!targetPoint.equals(other.targetPoint)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Destination [node=" + targetPoint + ", travelTime=" + travelTime + "]";
	}

}
