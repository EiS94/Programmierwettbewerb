package ProblemE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class Loesung2 {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new StringReader(args[0]));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int m = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);
        int k = Integer.parseInt(strings[2]);

        Socket[] sockets = new Socket[m];
        Cable[] cables = new Cable[n];
        for (int i = 0; i < m; i++) {
            sockets[i] = new Socket(i);
        }

        for (int i = 0; i < n; i++) {
            cables[i] = new Cable(i);
        }

        int sNum;
        int cNum;
        for (int i = 0; i < k; i++) {
            strings = br.readLine().split(" ");
            sNum = Integer.parseInt(strings[0]) - 1;
            cNum = Integer.parseInt(strings[1]) - 1;
            //add cable to socket
            sockets[sNum].addCable(cables[cNum]);
        }


        int numMatches = 0;
        int maxFlowOhne = 0;
        for (Socket socket:sockets) {
            if (socket.findMatch()){
                ++maxFlowOhne;
            }
        }

        for(Socket socket: sockets){
            socket.reset();
        }
        for(Cable cable:cables){
            cable.reset();
        }

        /*
        //Bigger graph = tempSocket
        Socket[] tempSocket = new Socket[m + 2];
        for (int i = 0; i < m; i++) {
            tempSocket[i] = sockets[i];
        }

        int maxMatches = 0;

        for (int i = 0; i < m; i++) {
            numMatches = 0;
            //start copy Socket i twice
            tempSocket[m] = new Socket(m);
            tempSocket[m + 1] = new Socket(m+1);
            for (Cable cable:tempSocket[i].cables){
                tempSocket[m].addCable(cable);
                tempSocket[m + 1].addCable(cable);
            }
            //end copy


            //Find biggest match in bigGraph
            for (int j = 0; j < m + 2; j++) {
                if (tempSocket[j].findMatch()){
                    ++numMatches;
                }
            }

            //reset for next BigGraph
            for(Socket socket: sockets){
                socket.reset();
            }
            for(Cable cable:cables){
                cable.reset();
            }

            //save maxMatch
            if (numMatches > maxMatches){
                maxMatches = numMatches;
            }
            if (maxMatches == n || maxMatches == m + 2 || maxMatches == maxFlowOhne + 2){
                break;
            }
        }*/

        System.out.print(maxFlowOhne);
    }

    public static class Socket{

        ArrayList<Cable> cables = new ArrayList<>();
        HashMap<Cable, Boolean> seen = new HashMap<>();

        int ID;
        public Socket(int ID){
            this.ID = ID + 1;
        }

        public boolean findMatch(){
            for (Cable cable:cables){
                if (!seen.get(cable)){
                    seen.put(cable, true);
                    if (cable.match == null || cable.match.findMatch()){
                        cable.match = this;
                        return true;
                    }
                }
            }
            return false;
        }

        public void addCable(Cable cable){
            seen.put(cable, false);
            cables.add(cable);
        }

        public void reset(){
            for (Cable cable:cables){
                seen.put(cable, false);
            }
        }
    }

    public static class Cable{

        int ID;
        public Cable(int ID){
            this.ID = ID + 1;
        }
        Socket match;

        public void reset(){
            match = null;
        }
    }
}
