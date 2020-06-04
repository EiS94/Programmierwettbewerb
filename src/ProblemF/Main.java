import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int villages = Integer.parseInt(br.readLine());
        List<Tuple> blueVillages = new LinkedList<>();
        List<Tuple> redVillages = new LinkedList<>();
        List<Tuple> greenVillages = new LinkedList<>();
        for (int i = 0; i < villages; i++) {
            String[] line = br.readLine().split(" ");
            if (Integer.parseInt(line[2]) == 1)
                blueVillages.add(new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            else if (Integer.parseInt(line[2]) == 2)
                redVillages.add(new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            else
                greenVillages.add(new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
        }
        br.close();


        if (blueVillages.size() > redVillages.size() && blueVillages.size() > greenVillages.size()) {
            //case blue is biggest -> blue is ignored

            //sort by X
            redVillages.sort(Tuple.comparatorByX);
            greenVillages.sort(Tuple.comparatorByX);

            //sort by Y
            redVillages.sort(Tuple.comparatorByY);
            greenVillages.sort(Tuple.comparatorByY);

            List<Coordinate> verticesRed = new LinkedList<>();
            List<Coordinate> verticesGreen = new LinkedList<>();

            //add red and green hub vertices
            verticesRed.add(new Coordinate(2999.5, -2998.5));
            verticesRed.add(new Coordinate(2999.5, -2999.5));
            verticesRed.add(new Coordinate(-2999.5, -2999.5));
            verticesRed.add(new Coordinate(-2999.5, -2998.5));

            verticesGreen.add(new Coordinate(-2999.5, 2998.5));
            verticesGreen.add(new Coordinate(-2999.5, 2999.5));
            verticesGreen.add(new Coordinate(2999.5, 2999.5));
            verticesGreen.add(new Coordinate(2999.5, 2998.5));


            //add all vertices for red villages
            double currentX = -2999.5;
            Coordinate lastCoordinate = new Coordinate(-2999.5, -2998.5);

            for (Tuple t : redVillages) {
                if (currentX != t.x - 0.48) {
                    if (lastCoordinate.y != -2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, -2998.5);
                        verticesRed.add(c);
                        lastCoordinate = c;
                    }
                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x - 0.49;
                    verticesRed.add(new Coordinate(currentX, -2998.5));
                    verticesRed.add(new Coordinate(currentX, 2998.49));
                    currentX = t.x - 0.48;
                    lastCoordinate = new Coordinate(currentX, 2998.49);
                    verticesRed.add(lastCoordinate);
                }
                //add the box arround the village
                verticesRed.add(new Coordinate(currentX, t.y + 0.1));
                verticesRed.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y + 0.1));
                verticesRed.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y - 0.1));
                lastCoordinate = new Coordinate(currentX, t.y - 0.1);
                verticesRed.add(lastCoordinate);
            }
            verticesRed.add(new Coordinate(currentX, -2998.5));


            //add all vertices for green villages
            currentX = 2999.5;
            lastCoordinate = new Coordinate(-2999.5, 2998.5);

            for (int i = greenVillages.size() - 1; i >= 0; i--) {
                Tuple t = greenVillages.get(i);
                if (currentX != t.x + 0.48) {
                    if (lastCoordinate.y != 2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, 2998.5);
                        verticesGreen.add(c);
                        lastCoordinate = c;
                    }

                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x + 0.49;
                    verticesGreen.add(new Coordinate(currentX, 2998.5));
                    verticesGreen.add(new Coordinate(currentX, -2998.49));
                    currentX = t.x + 0.48;
                    lastCoordinate = new Coordinate(currentX, -2998.49);
                    verticesGreen.add(lastCoordinate);
                }

                //add the box arround the village
                verticesGreen.add(new Coordinate(currentX, t.y - 0.1));
                verticesGreen.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y - 0.1));
                verticesGreen.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y + 0.1));
                lastCoordinate = new Coordinate(currentX, t.y + 0.1);
                verticesGreen.add(lastCoordinate);
            }

            verticesGreen.add(new Coordinate(currentX, 2998.5));

            StringBuilder sb = new StringBuilder();
            sb.append(verticesRed.size() + "\n");
            for (Coordinate c : verticesRed) {
                sb.append(c.toString() + "\n");
            }
            sb.append(verticesGreen.size() + "\n");
            for (Coordinate c : verticesGreen) {
                sb.append(c.toString() + "\n");
            }
            sb.setLength(sb.length()-1);
            System.out.print(sb.toString());

        } else if (redVillages.size() > blueVillages.size() && redVillages.size() > greenVillages.size()) {
            //case red is biggest -> red is ignored

            //sort by X
            blueVillages.sort(Tuple.comparatorByX);
            greenVillages.sort(Tuple.comparatorByX);

            //sort by Y
            blueVillages.sort(Tuple.comparatorByY);
            greenVillages.sort(Tuple.comparatorByY);

            List<Coordinate> verticesBlue = new LinkedList<>();
            List<Coordinate> verticesGreen = new LinkedList<>();

            //add blue and green hub vertices
            verticesBlue.add(new Coordinate(2999.5, -2998.5));
            verticesBlue.add(new Coordinate(2999.5, -2999.5));
            verticesBlue.add(new Coordinate(-2999.5, -2999.5));
            verticesBlue.add(new Coordinate(-2999.5, -2998.5));

            verticesGreen.add(new Coordinate(-2999.5, 2998.5));
            verticesGreen.add(new Coordinate(-2999.5, 2999.5));
            verticesGreen.add(new Coordinate(2999.5, 2999.5));
            verticesGreen.add(new Coordinate(2999.5, 2998.5));


            //add all vertices for blue villages
            double currentX = -2999.5;
            Coordinate lastCoordinate = new Coordinate(-2999.5, -2998.5);

            for (Tuple t : blueVillages) {
                if (currentX != t.x - 0.48) {
                    if (lastCoordinate.y != -2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, -2998.5);
                        verticesBlue.add(c);
                        lastCoordinate = c;
                    }
                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x - 0.49;
                    verticesBlue.add(new Coordinate(currentX, -2998.5));
                    verticesBlue.add(new Coordinate(currentX, 2998.49));
                    currentX = t.x - 0.48;
                    lastCoordinate = new Coordinate(currentX, 2998.49);
                    verticesBlue.add(lastCoordinate);
                }
                //add the box arround the village
                verticesBlue.add(new Coordinate(currentX, t.y + 0.1));
                verticesBlue.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y + 0.1));
                verticesBlue.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y - 0.1));
                lastCoordinate = new Coordinate(currentX, t.y - 0.1);
                verticesBlue.add(lastCoordinate);
            }
            verticesBlue.add(new Coordinate(currentX, -2998.5));


            //add all vertices for green villages
            currentX = 2999.5;
            lastCoordinate = new Coordinate(-2999.5, 2998.5);

            for (int i = greenVillages.size()-1; i >= 0; i--) {
                Tuple t = greenVillages.get(i);
                if (currentX != t.x + 0.48) {
                    if (lastCoordinate.y != 2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, 2998.5);
                        verticesGreen.add(c);
                        lastCoordinate = c;
                    }

                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x + 0.49;
                    verticesGreen.add(new Coordinate(currentX, 2998.5));
                    verticesGreen.add(new Coordinate(currentX, -2998.49));
                    currentX = t.x + 0.48;
                    lastCoordinate = new Coordinate(currentX, -2998.49);
                    verticesGreen.add(lastCoordinate);
                }

                //add the box arround the village
                verticesGreen.add(new Coordinate(currentX, t.y - 0.1));
                verticesGreen.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y - 0.1));
                verticesGreen.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y + 0.1));
                lastCoordinate = new Coordinate(currentX, t.y + 0.1);
                verticesGreen.add(lastCoordinate);
            }

            verticesGreen.add(new Coordinate(currentX, 2998.5));

            StringBuilder sb = new StringBuilder();
            sb.append(verticesBlue.size() + "\n");
            for (Coordinate c : verticesBlue) {
                sb.append(c.toString() + "\n");
            }
            sb.append(verticesGreen.size() + "\n");
            for (Coordinate c : verticesGreen) {
                sb.append(c.toString() + "\n");
            }
            sb.setLength(sb.length()-1);
            System.out.print(sb.toString());

        } else {
            //case green is biggest -> green is ignored

            //sort by X
            blueVillages.sort(Tuple.comparatorByX);
            redVillages.sort(Tuple.comparatorByX);

            //sort by Y
            blueVillages.sort(Tuple.comparatorByY);
            redVillages.sort(Tuple.comparatorByY);

            List<Coordinate> verticesBlue = new LinkedList<>();
            List<Coordinate> verticesRed = new LinkedList<>();

            //add blue and green hub vertices
            verticesBlue.add(new Coordinate(2999.5, -2998.5));
            verticesBlue.add(new Coordinate(2999.5, -2999.5));
            verticesBlue.add(new Coordinate(-2999.5, -2999.5));
            verticesBlue.add(new Coordinate(-2999.5, -2998.5));

            verticesRed.add(new Coordinate(-2999.5, 2998.5));
            verticesRed.add(new Coordinate(-2999.5, 2999.5));
            verticesRed.add(new Coordinate(2999.5, 2999.5));
            verticesRed.add(new Coordinate(2999.5, 2998.5));


            //add all vertices for blue villages
            double currentX = -2999.5;
            Coordinate lastCoordinate = new Coordinate(-2999.5, -2998.5);

            for (Tuple t : blueVillages) {
                if (currentX != t.x - 0.48) {
                    if (lastCoordinate.y != -2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, -2998.5);
                        verticesBlue.add(c);
                        lastCoordinate = c;
                    }
                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x - 0.49;
                    verticesBlue.add(new Coordinate(currentX, -2998.5));
                    verticesBlue.add(new Coordinate(currentX, 2998.49));
                    currentX = t.x - 0.48;
                    lastCoordinate = new Coordinate(currentX, 2998.49);
                    verticesBlue.add(lastCoordinate);
                }
                //add the box arround the village
                verticesBlue.add(new Coordinate(currentX, t.y + 0.1));
                verticesBlue.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y + 0.1));
                verticesBlue.add(new Coordinate(Math.round((currentX + 0.8)*100)/100.0, t.y - 0.1));
                lastCoordinate = new Coordinate(currentX, t.y - 0.1);
                verticesBlue.add(lastCoordinate);
            }
            verticesBlue.add(new Coordinate(currentX, -2998.5));


            //add all vertices for green villages
            currentX = 2999.5;
            lastCoordinate = new Coordinate(-2999.5, 2998.5);

            for (int i = redVillages.size() - 1; i >= 0; i--) {
                Tuple t = redVillages.get(i);
                if (currentX != t.x + 0.48) {
                    if (lastCoordinate.y != 2998.5) {
                        //add new vertical line to bottom
                        Coordinate c = new Coordinate(currentX, 2998.5);
                        verticesRed.add(c);
                        lastCoordinate = c;
                    }

                    //add new horizontal line to next x and new vertical line to top and new small horizontal line
                    currentX = t.x + 0.49;
                    verticesRed.add(new Coordinate(currentX, 2998.5));
                    verticesRed.add(new Coordinate(currentX, -2998.49));
                    currentX = t.x + 0.48;
                    lastCoordinate = new Coordinate(currentX, -2998.49);
                    verticesRed.add(lastCoordinate);
                }

                //add the box arround the village
                verticesRed.add(new Coordinate(currentX, t.y - 0.1));
                verticesRed.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y - 0.1));
                verticesRed.add(new Coordinate(Math.round((currentX - 0.8)*100)/100.0, t.y + 0.1));
                lastCoordinate = new Coordinate(currentX, t.y + 0.1);
                verticesRed.add(lastCoordinate);
            }

            verticesRed.add(new Coordinate(currentX, 2998.5));

            StringBuilder sb = new StringBuilder();
            sb.append(verticesBlue.size() + "\n");
            for (Coordinate c : verticesBlue) {
                sb.append(c.toString() + "\n");
            }
            sb.append(verticesRed.size() + "\n");
            for (Coordinate c : verticesRed) {
                sb.append(c.toString() + "\n");
            }
            sb.setLength(sb.length()-1);
            System.out.print(sb.toString());

        }


    }

    private static class Coordinate {

        double x, y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private static class Tuple {

        int x, y, color;

        public Tuple(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return x == tuple.x &&
                    y == tuple.y &&
                    color == tuple.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, color);
        }

        @Override
        public String toString() {
            String c;
            if (color == 1) c = "blue";
            else if (color == 2) c = "red";
            else c = "green";
            return "x: " + x + ", y: " + y + ", " + c;
        }

        static Comparator<Tuple> comparatorByX = new Comparator<Tuple>() {
            @Override
            public int compare(Tuple tuple, Tuple t1) {
                if (tuple.x < t1.x) return -1;
                else if (tuple.x > t1.x) return 1;
                else return 0;
            }
        };

        static Comparator<Tuple> comparatorByY = new Comparator<Tuple>() {
            @Override
            public int compare(Tuple tuple, Tuple t1) {
                if (tuple.x == t1.x && tuple.y > t1.y) return -1;
                else if (tuple.x == t1.x && tuple.y < t1.y) return 1;
                else return 0;
            }
        };

    }
}

