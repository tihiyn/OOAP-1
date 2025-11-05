abstract class ABloomFilter<T> {

    // ------------------- запросы -------------------

    // постусловие: создан пустой фильтр заданного размера
    public abstract ABloomFilter<T> ABloomFilter(int len); // конструктор

    // постусловие: если значение ранее было добавлено, то метод вернёт true
    public abstract boolean isValue(T itm);


    // ------------------- команды -------------------

    // постусловие: в фильтр добавлено заданное значение
    public abstract void add(T itm);
}
