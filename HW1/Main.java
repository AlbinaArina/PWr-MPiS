import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        City[] cities = new City[]{new City(4, 7), new City(5, -120), new City(8, -1), new City(70, 23)};
        new Main().neighbourModified(cities);
    }

    public void neighbourModified(City[] cities) {
        List<Double> distances = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            distances.add(lengthOfThePath(getPath(cities[i], cities)));
        }
        int indexOfCityWithShortestPath = distances.indexOf(Collections.min(distances));
        System.out.println(distances);
        System.out.println(getPath(cities[indexOfCityWithShortestPath], cities));
    }

    public List<City> getPath(City startingCity, City[] cities) {
        List<City> visitedCities = new ArrayList<>();
        List<City> path = new ArrayList<>();
        visitedCities.add(startingCity);
        path.add(startingCity);
        while (visitedCities.size() < cities.length) {
            City currentCity = path.get(path.size() - 1);
            double minDistance = Double.MAX_VALUE;
            City nearestCity = currentCity;
            for (City c : cities) {
                if (!visitedCities.contains(c)) {
                    double distanceBetweenCities = getDistance(currentCity, c);
                    if (distanceBetweenCities < minDistance) {
                        minDistance = distanceBetweenCities;
                        nearestCity = c;
                    }
                }
            }
            path.add(nearestCity);
            visitedCities.add(nearestCity);
        }
        return path;
    }

    public double lengthOfThePath(List<City> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += getDistance(path.get(i), path.get(i + 1));
        }
        return distance;
    }

    public double getDistance(City a, City b) {
        return Math.sqrt((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x));
    }


    public static class City {
        int x;
        int y;
        public City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "City{" + "x=" + x + ", y=" + y + '}';
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
