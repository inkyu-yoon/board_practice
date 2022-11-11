package mustache.practice.parser;

public interface Parser<T> {
    T parse(String str);
}
