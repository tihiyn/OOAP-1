abstract class APowerSet extends AHashTable {

    // ------------------- статусы -------------------
    public final int PUT_FULL = 0;
    public final int PUT_EXIST = 2;

    // ------------------- запросы -------------------

    // постусловие: создано пустое множество заданного размера
    public abstract APowerSet PowerSet(int size); // конструктор

    public abstract APowerSet intersection(APowerSet other);
    public abstract APowerSet union(APowerSet other);
    public abstract APowerSet difference(APowerSet other);
    public abstract APowerSet isSubset(APowerSet other);
    public abstract boolean equals(APowerSet other);

    // ------------------- команды -------------------

    // предусловие: эл-нт отсутствует в множестве и в множестве есть свободное место
    // постусловие: эл-нт добавлен в множество
    public abstract void put(String itm);
}
