abstract class NativeDictionary<T> {

    // ------------------- статусы -------------------
    public final int PUT_FULL = 0;
    public final int PUT_OK = 1;

    public final int GET_NIL = 0;
    public final int GET_OK = 1;

    // ------------------- запросы -------------------

    // постусловие: создан пустой ассоциативный массив заданного размера
    public abstract NativeDictionary<T> NativeDirectory(int size); // конструктор

    // предусловие: заданный ключ существует
    public abstract T get(String key);

    public abstract boolean isKey(String key);

    public abstract int size();

    // ------------------- команды -------------------

    // предусловие: в ассоциативном массиве есть свободное место
    // постусловие: если заданный ключ уже существует, то значение перезаписано и равно заданному.
    // Иначе в ассоциативный массив добавлена заданная пара ключ-значение
    public abstract void put(String key, T value);

    // ------------------- запросы статусов -------------------
    public abstract int getPutStatus();
    public abstract int getGetStatus();
}
