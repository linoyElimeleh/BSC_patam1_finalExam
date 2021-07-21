package test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PointManager {

    public static boolean anyMatch(List<Point> ps, Point p) {
        for (Point point : ps) {
            if (point.x == p.x && point.y == p.y) {
                return true;
            }
        }
        return false;
    }

    public static List<Point> orderByX(List<Point> ps) {
        List<Point> r = new ArrayList<Point>();
        r.addAll(ps);
        r.sort((p1, p2) -> p1.x - p2.x);
        return r;

    }

    public static Map<Integer, List<Point>> groupingByX(List<Point> ps) {
        Map<Integer, List<Point>> map = new HashMap<>();

        for (Point point : ps) {
            Integer x = point.x;
            if (!map.containsKey(x)) {
                map.put(x, new ArrayList<>());
            }
            map.get(x).add(point);
        }
        return map;
    }

    public static Map<Integer, List<Point>> groupingByY(List<Point> ps) {
        Map<Integer, List<Point>> map = new HashMap<>();

        for (Point point : ps) {
            Integer y = point.y;
            if (!map.containsKey(y)) {
                map.put(y, new ArrayList<>());
            }
            map.get(y).add(point);
        }
        return map;
    }

    public static Point mostFrequent(List<Point> ps) {
        Map<Integer, List<Point>> map = groupingByX(ps);
        Point biggestPoint = null;
        int biggestY = -1;
        int biggestYCount = -1;

        for (Integer key : map.keySet()) {
            List<Point> relevantPoints = map.get(key);


            for (int i = 0; i < relevantPoints.size() - 1; i++) {

                Point currentPoint = relevantPoints.get(i);
                int howMuchYForCurrent = 0;

                for (int j = i + 1; j < relevantPoints.size() - 1; j++) {
                    if (currentPoint.y == relevantPoints.get(j).y) {
                        howMuchYForCurrent++;
                    }
                }
                if (howMuchYForCurrent > biggestYCount) {
                    biggestYCount = howMuchYForCurrent;
                    biggestY = currentPoint.y;
                    biggestPoint = new Point(relevantPoints.get(i).x, biggestY);
                }
            }
        }
        return biggestPoint;
    }

    public static void saveToFile(String fileName, List<Point> ps) throws Exception {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            ps.forEach(si -> out.println(si.x + "\n" + si.y));
            out.close();
        } catch (Exception e) {
        }
    }

    public static List<Point> loadFile(String fileName) throws Exception {
        List<Point> ps = new ArrayList<>();
        try {
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (s.hasNext()) {
                int x = s.nextInt();
                int y = s.nextInt();
                Point p = new Point(x, y);
                ps.add(p);
            }
            s.close();
            return ps;
        } catch (Exception e) {
        }

        return ps;
    }
}
