package studiii.zlsj_test.gps;

/**
 * @Author: liaosijun
 * @Time: 2019/12/27 15:55
 */
public class MapPoint {
	private double lng;
	private double lat;

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public static MapPoint NewPoint(double lng, double lat) {
		MapPoint newPoint = new MapPoint();
		newPoint.setLng(lng);
		newPoint.setLat(lat);
		return newPoint;
	}
}
