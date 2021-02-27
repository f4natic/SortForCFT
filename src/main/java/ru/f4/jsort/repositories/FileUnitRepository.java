package ru.f4.jsort.repositories;

import ru.f4.jsort.model.FileUnit;
import ru.f4.jsort.utils.FileSort;

import java.util.*;

public class FileUnitRepository {
    private List<FileUnit> fileUnitList;

    public FileUnitRepository() {
        fileUnitList = new LinkedList<>();
    }

    public void addFileUnit(String name) {
        fileUnitList.add(new FileUnit(name));
    }

    public void sort(String order, String type) {
        if(fileUnitList.isEmpty()){
            System.out.println("NO DATA FOR SORT");
            System.exit(0);
        }
        fileUnitList.forEach(u -> FileSort.sortFile(u, order, type));
    }

    public void removeNonexistent() {
        Iterator<FileUnit> iterator = fileUnitList.iterator();
        while(iterator.hasNext()) {
            if(!iterator.next().isExist()){
                iterator.remove();
            }
        }
    }

    public void removeDublicate(){
        HashMap<FileUnit, Boolean> map = new HashMap<>();
        Iterator<FileUnit> itr = fileUnitList.iterator();
        while(itr.hasNext()){
            FileUnit nextNode = itr.next();
            if(map.containsKey(nextNode)){
                itr.remove();
            } else {
                map.put(nextNode, true);
            }
        }
    }

    public List<FileUnit> getFileUnitList() {
        return Collections.unmodifiableList(fileUnitList);
    }
}
