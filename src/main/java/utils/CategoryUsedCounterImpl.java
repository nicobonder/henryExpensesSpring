/*
package utils;

import dto.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryUsedCounterImpl implements CategoryUsedCounter {

    @Override
    public void countCategoryUsed(List<Expense> expenses) {
        //Metodo para hacer un map que registre la cantidad de veces que aparece cada categoria
        Map<String, Integer> categoryCounter = new HashMap<>();
        int counter = 0;
        for(Expense expense : expenses){
            String categoryName = expense.getCategory().getCategoryName();

            if(categoryCounter.containsKey(categoryName)){
                counter++;
            } else {
                counter = 1;
            }
            categoryCounter.put(categoryName, counter);
        }
        System.out.println("Esta es la cantidad de veces que gastaste en cada categoria");
        for(Map.Entry<String, Integer> entry : categoryCounter.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
*/
