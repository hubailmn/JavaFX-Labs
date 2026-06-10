package snakegame.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameData {

    File dataFile;

    private static GameData instance = new GameData();

    public GameData() {
        String name = "GameData";

        this.dataFile = new File(name + ".dat");
        if (!dataFile.exists()) {
            try {
                var createdFile = dataFile.createNewFile();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static GameData getInstance() {
        return instance;
    }

    public void saveModal(StatisticModal modal) {
        try (DataOutputStream dat = new DataOutputStream(new FileOutputStream(dataFile, true))) {
            dat.writeLong(modal.getScore());
            dat.writeLong(modal.getTime());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<StatisticModal> loadModals() {
        List<StatisticModal> statisticModals = new ArrayList<>();

        try (DataInputStream dat = new DataInputStream(new FileInputStream(dataFile))) {

            while (dat.available() > 0) {
                statisticModals.add(new StatisticModal(dat.readLong(), dat.readLong()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return statisticModals;
    }
}
