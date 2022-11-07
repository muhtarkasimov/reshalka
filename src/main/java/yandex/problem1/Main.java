package yandex.problem1;

import java.util.*;
import java.util.stream.Collectors;

public class Problem1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<DataCenter> dataCenters = new ArrayList<>();

        //команды
        final String DISABLE = "DISABLE";
        final String RESET = "RESET";
        final String GET_MAX = "GETMAX";
        final String GET_MIN = "GETMIN";

        //считать кол-во вводов
        int dataCentersNum = scan.nextInt();
        int serversNum = scan.nextInt();
        int inputs = scan.nextInt();
        scan.nextLine();

        //заполнить дата центры
        for (int i = 1; i <= dataCentersNum; i++) {
            DataCenter dc = new DataCenter(i, serversNum);
            dataCenters.add(dc);
        }

        //проходка по командам
        for (int i = 0; i < inputs; i++) {
            String line = scan.nextLine();
            if (line.contains(DISABLE)) {
                String[] words = line.split(" ");
                int serverId = Integer.parseInt(words[2]);
                int dataCenterId = Integer.parseInt(words[1]);
                dataCenters.get(dataCenterId - 1).getServers().get(serverId - 1).setActive(false);
            } else if (line.contains(RESET)) {
                String[] words = line.split(" ");
                int dataCenterId = Integer.parseInt(words[1]);
                dataCenters.get(dataCenterId - 1).reset();
            } else if (line.contains(GET_MAX)) {
                long max = Long.MIN_VALUE;
                int maxDcId = 1;
                for (DataCenter dc : dataCenters) {
                    if (dc.getResetsXServersAmount() > max) {
                        max = dc.getResetsXServersAmount();
                        maxDcId = dc.getId();
                    }
                }
                System.out.println(maxDcId);
            } else if (line.contains(GET_MIN)) {
                long min = Long.MAX_VALUE;
                int minDcId = 1;
                for (DataCenter dc : dataCenters) {
                    if (dc.getResetsXServersAmount() < min) {
                        min = dc.getResetsXServersAmount();
                        minDcId = dc.getId();
                    }
                }
                System.out.println(minDcId);
            }
        }
    }
}

class DataCenter {
    private final int id;
    private final List<Server> servers;
    private int resets;

    public DataCenter(int id, int serverNums) {
        this.id = id;
        servers = new ArrayList<>();
        resets = 1;
        fillServers(serverNums);
    }

    private void fillServers(int serversNums) {
        for (int i = 1; i <= serversNums; i++) {
            servers.add(new Server(i));
        }
    }

    void reset() {
        resets++;
        for (Server s : servers) {
            s.setActive(true);
        }
    }

    long getResetsXServersAmount() {
        long activeServersNum = servers.stream().filter(Server::isActive).count();
        return resets * activeServersNum;
    }

    public int getId() {
        return id;
    }

    public List<Server> getServers() {
        return servers;
    }

    public int getResets() {
        return resets;
    }
}

class Server {
    private final int id;
    private boolean isActive;

    public Server(int id) {
        this.id = id;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}