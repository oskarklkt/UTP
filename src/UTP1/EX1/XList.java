package UTP1.EX1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    @SafeVarargs
    public XList(T... elements) {
        super(Arrays.asList(elements));
    }

    public XList(Collection<T> elements) {
        super(elements);
    }

    @SafeVarargs
    public static <T> XList<T> of(T... elements) {
        return new XList<>(elements);
    }

    public static <T> XList<T> of(Collection<T> elements) {
        return new XList<>(elements);
    }

    public static XList<String> charsOf(String string) {
        return new XList<>(string.split(""));
    }

    public static XList<String> tokensOf(String string) {
        return new XList<>(string.split(" "));
    }

    public static XList<String> tokensOf(String string, String sep) {
        return new XList<>(string.split(sep));
    }

    public XList<T> union(Collection<T> elements) {
        XList<T> result = new XList<>(this);
        result.addAll(elements);
        return result;
    }

    public XList<T> union(T[] elements) {
        XList<T> result = new XList<>(this);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public XList<T> diff(Collection<T> elements) {
        return this.stream()
                .filter(e -> !elements.contains(e))
                .collect(Collectors.toCollection(XList<T>::new));
    }

    public XList<T> unique() {
        return this.stream()
                .distinct()
                .collect(Collectors.toCollection(XList<T>::new));
    }

    public XList<XList<T>> combine() {
        List<List<T>> lists = (List<List<T>>) this;
        XList<XList<T>> result = new XList<>();

        int totalCombinations = lists.stream()
                                     .mapToInt(List::size)
                                     .reduce(1, (a, b) -> a * b);

        for (int i = 0; i < totalCombinations; i++) {
            XList<T> combination = new XList<>();
            int index = i;

            for (List<T> list : lists) {
                combination.add(list.get(index % list.size()));
                index /= list.size();
            }

            result.add(combination);
        }

        return result;
    }

    public <U> XList<U> collect(Function<T, U> function) {
        return this.stream().map(function).collect(Collectors.toCollection(XList<U>::new));
    }

    public String join() {
        return this.stream().map(Object::toString).collect(Collectors.joining());
    }

    public String join(String sep) {
        return this.stream().map(Object::toString).collect(Collectors.joining(sep));
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }

}
