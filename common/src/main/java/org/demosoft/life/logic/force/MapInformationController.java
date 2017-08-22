package org.demosoft.life.logic.force;

import lombok.Getter;
import org.demosoft.life.logic.format.XFormatter;
import org.demosoft.life.logic.statistic.Statistic;
import org.demosoft.life.model.GameMap;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class MapInformationController implements Serializable {

    @Getter
    private final EventLog eventLog;
    @Getter
    private final Statistic statistic;

    private int cellsCount;
    private int cellsWaterCount;
    private int cellsLandCount;
    private int peopleCount;
    private int peopleMenCount;
    private int peopleWomenCount;
    private int peopleWomenPregnantCount;
    private int childrenWereBornCount;
    private int childrenDiedCount;
    private int peopleDiedCount;
    private int peopleDiedByEnergyCount;
    private int peopleDiedBySatietyCount;
    private int peopleDiedByAgeCount;
    private int peopleDiedByLostCount;
    private float peopleLandDensityCount;
    private int plantsCount;
    private int plantsFruitsCount;
    private float plantsLandDensityCount;
    private float plantsFruitsMean;
    private float plantsFruitsPeopleRatio;
    private String formatDateShort;
    private String formatDate;

    public MapInformationController(GameMap gameMap) {
        this.eventLog = new EventLog();
        this.statistic = new Statistic(gameMap);
    }

    public final void update(int days) {
        cellsCount = statistic.CELLS;
        cellsWaterCount = statistic.CELLS_WATER;
        cellsLandCount = statistic.CELLS_LAND;
        peopleCount = statistic.people;
        peopleMenCount = statistic.peopleMen;
        peopleWomenCount = statistic.peopleWomen;
        peopleWomenPregnantCount = statistic.peopleWomenPregnant;
        childrenWereBornCount = statistic.childrenWereBorn;
        childrenDiedCount = statistic.childrenDied;
        peopleDiedCount = statistic.peopleDied;
        peopleDiedByEnergyCount = statistic.peopleDiedByEnergy;
        peopleDiedBySatietyCount = statistic.peopleDiedBySatiety;
        peopleDiedByAgeCount = statistic.peopleDiedByAge;
        peopleDiedByLostCount = statistic.peopleDiedByLost;
        peopleLandDensityCount = statistic.getPeopleLandDensity();
        plantsCount = statistic.plants;
        plantsFruitsCount = statistic.plantsFruits;
        plantsLandDensityCount = statistic.getPlantsLandDensity();
        plantsFruitsMean = statistic.getPlantsFruitsMean();
        plantsFruitsPeopleRatio = statistic.getPlantsFruitsPeopleRatio();
        formatDateShort = XFormatter.formatDateShort(days);
        formatDate = XFormatter.formatDate((int) statistic.getPeopleAgeMean());

    }
}
