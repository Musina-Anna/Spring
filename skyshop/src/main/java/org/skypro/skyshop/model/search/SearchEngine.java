package org.skypro.skyshop.model.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> comparator = (o1, o2) -> {
            int lengthCompare = Integer.compare(o2.getName().length(), o1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            return o1.getName().compareTo(o2.getName());
        };

        return items.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }


    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestResult = null;
        int maxCount = 0;

        for (Searchable item : items) {
            if (item != null) {
                int count = countOccurrences(item.getSearchTerm(), search);
                if (count > maxCount) {
                    maxCount = count;
                    bestResult = item;
                }
            }
        }
        if (bestResult == null) {
            throw new BestResultNotFound(search);
        }
        return bestResult;
    }

    private int countOccurrences(String source, String search) {
        if (search == null || search.isEmpty()) return 0;
        int count = 0;
        int index = 0;
        int sublndex = source.indexOf(search, index);
        while (sublndex != -1) {
            count++;
            index = sublndex + search.length();
            sublndex = source.indexOf(search, index);
        }
        return count;
    }


}


