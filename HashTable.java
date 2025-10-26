abstract class AHashTable<T> {
    // ------------------- статусы -------------------

    public final int PUT_COLLISION = 0;
    public final int PUT_OK = 1;
    public final int PUT_FULL = 2;

    public final int REMOVE_COLLISION = 0;
    public final int REMOVE_OK = 1;

    public final int CONTAINS_COLLISION = 0;
    public final int CONTAINS_OK = 1;

    // ------------------- запросы -------------------

    // постусловие: создана пустая хэш-таблица размера `size`
//    public abstract AHashTable(int size); // конструктор

    public abstract boolean contains(T itm);



    // ------------------- команды -------------------

    // предусловие: в хэш-таблице есть свободное место
    // постусловие: в хэш-таблицу добавлен эл-нт со значением `itm`, при условии, что не произошло коллизии
    public abstract void put(T itm);

    // постусловие: из хэш-таблицы удалён эл-нт со значением `itm`, при условии, что не произошло коллизии
    public abstract void remove(T itm);


    // ------------------- запросы статусов -------------------

    public abstract int getPutStatus();
    public abstract int getRemoveStatus();
    public abstract int getContainsStatus();
}

