/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionar;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author tifui
 */
public class TreeVocabulary extends TreeSet<String> implements Vocabulary {

    public TreeVocabulary(Collection<String> c) {
        super(c);
    }

    @Override
    public boolean isPrefix(String prefix) {
        String nextWord = ceiling(prefix);
        if (nextWord == null) {
            return false;
        }
        if (nextWord.equals(prefix)) {
            Set<String> tail = tailSet(nextWord, false);
            if (tail.isEmpty()) {
                return false;
            }
            nextWord = tail.iterator().next();
        }
        return nextWord.startsWith(prefix);
    }

    /**
     * There is a mismatch between the parameter types of vocabulary and TreeSet, so
     * force call to the upper-class method
     * @param word
     * @return 
     */
    @Override
    public boolean contains(String word) {
        return super.contains(word);
    }
}
