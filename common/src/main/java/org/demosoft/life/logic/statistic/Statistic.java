package org.demosoft.life.logic.statistic;

import org.demosoft.life.model.Cell;
import org.demosoft.life.model.GameMap;
import org.demosoft.life.model.Human;
import org.demosoft.life.model.impl.MapFactoryImpl;
import org.demosoft.life.model.type.HumanType;
import org.demosoft.life.model.type.PlantType;

import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 2/10/2017.
 */
public class Statistic implements Serializable {

    // LANDSCAPE
    public  final int CELLS = 4225;
    public  final int CELLS_WATER = 1713;
    public  final int CELLS_LAND = 2512;
    // HUMAN
    public  int people = 0;
    public  int peopleMen = 0;
    public  int peopleWomen = 0;
    public  int peopleWomenPregnant = 0;
    public  int peopleAge = 0;
    public  int childrenWereBorn = 0;
    public  int childrenDied = 0;
    public  int peopleDied = 0;
    public  int peopleDiedByAge = 0;
    public  int peopleDiedByEnergy = 0;
    public  int peopleDiedBySatiety = 0;
    public  int peopleDiedByLost = 0;
    // PLANT
    public  int plants = 0;
    public  int plantsFruits = 0;

    public Statistic(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    // DENSITY
    public  float getPeopleLandDensity() {
        return (float) people / CELLS_LAND;
    }

    public  float getPlantsLandDensity() {
        return (float) plants / CELLS_LAND;
    }

    // MEAN
    public  float getPeopleAgeMean() {
        return people != 0 ? (float) peopleAge / people : 0;
    }

    public  float getPlantsFruitsMean() {
        return plants != 0 ? (float) plantsFruits / plants : 0;
    }

    // RATIO
    public  float getPlantsFruitsPeopleRatio() {
        return people != 0 ? (float) plantsFruits / people : plantsFruits;
    }

    private final GameMap gameMap;



    public void update() {
        int peopleTemp = 0;
        int peopleMenTemp = 0;
        int peopleWomenTemp = 0;
        int peopleWomenPregnantTemp = 0;
        int peopleAgeTemp = 0;
        int plantsTemp = 0;
        int plantsFruitsTemp = 0;
        for (int y = 0; y < gameMap.getMapSize(); y++) {
            for (int x = 0; x < gameMap.getMapSize(); x++) {
                Cell cell = gameMap.getCellAt(x,y);
                Human human = cell.getHuman();
                if (human.getType() != HumanType.HUMAN_TYPE_EMPTY) {
                    peopleTemp++;
                    peopleAgeTemp += human.getAge();
                    if (human.getType() == HumanType.HUMAN_TYPE_MAN) {
                        peopleMenTemp++;
                    } else if (human.getType() == HumanType.HUMAN_TYPE_WOMAN) {
                        peopleWomenTemp++;
                        if (human.getPregnancy() != 0) {
                            peopleWomenPregnantTemp++;
                        }
                    }
                }
                if (cell.getPlant().getType() != PlantType.PLANT_TYPE_EMPTY) {
                    plantsTemp++;
                    plantsFruitsTemp += cell.getPlant().getFruits();
                }
            }
        }
        people = peopleTemp;
        peopleMen = peopleMenTemp;
        peopleWomen = peopleWomenTemp;
        peopleWomenPregnant = peopleWomenPregnantTemp;
        peopleAge = peopleAgeTemp;
        plants = plantsTemp;
        plantsFruits = plantsFruitsTemp;
    }

    public void reset() {
        people = 0;
        peopleMen = 0;
        peopleWomen = 0;
        peopleWomenPregnant = 0;
        peopleAge = 0;
        childrenWereBorn = 0;
        childrenDied = 0;
        peopleDied = 0;
        peopleDiedByAge = 0;
        peopleDiedByEnergy = 0;
        peopleDiedBySatiety = 0;
        peopleDiedByLost = 0;
        plants = 0;
        plantsFruits = 0;
    }
}
