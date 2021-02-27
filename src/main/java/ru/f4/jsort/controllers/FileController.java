package ru.f4.jsort.controllers;

import ru.f4.jsort.model.InputData;
import ru.f4.jsort.repositories.FileUnitRepository;
import ru.f4.jsort.utils.DataUtils;


import java.nio.file.Paths;

public class FileController {
    private FileUnitRepository fileUnitRepository;

    private InputData inputData;

    public FileController(InputData inputData) {
        this.inputData = inputData;
        fileUnitRepository = new FileUnitRepository();
    }

    public void run() {
        for(String line : inputData.getInput()) {
            fileUnitRepository.addFileUnit(line);
        }
        fileUnitRepository.removeDublicate();
        fileUnitRepository.removeNonexistent();
        fileUnitRepository.sort(inputData.getOrder(), inputData.getType());
        DataUtils.moveAndDeleteTemp(Paths.get("Temp"), Paths.get(inputData.getOutname()));
    }
}
