import java.lang.reflect.Array;

abstract class ADynArray<T> {
    // ------------------- статусы -------------------

    public final int GET_ITEM_INDEX_OUT_OF_BOUND = 0;
    public final int GET_ITEM_OK = 1;

    public final int INSERT_INDEX_OUT_OF_BOUND = 0;
    public final int INSERT_OK = 1;

    public final int REMOVE_INDEX_OUT_OF_BOUND = 0;
    public final int REMOVE_OK = 1;

    // ------------------- запросы -------------------

    // постусловие: создан пустой массив ёмкостью 16
    // public abstract ADynArray ADynArray(Class clz); // конструктор

    // предусловие: `idx` >= 0 и `idx` < кол-ва эл-ов в массиве
    public abstract T getItem(int idx);

    // ------------------- команды -------------------

    // предусловие: `idx` >= 0 и `idx` <= кол-ва эл-ов в массиве
    // постусловие: если кол-во элементов в массиве перед добавлением равно его ёмкости, то ёмкость массива увеличена вдвое
    // в массив добавлен элемент `itm` по заданному индексу
    // элементы с индексом больше `idx` сдвинуты на одну позицию вправо
    public abstract void insert(T itm, int idx);

    // предусловие: `idx` >= 0 и `idx` < кол-ва эл-ов в массиве
    // постусловие: из массива удалён элемент по заданному индексу
    // элементы с индексом больше `idx` сдвинуты на одну позицию влево
    // если заполненность массива после удаления меньше 50%, то уменьшить ёмкость до максимального из 16 и (текущая ёмкость / 1.5 и отбросить дробную часть)
    public abstract void remove(int idx);

    // постусловие: если кол-во элементов в массиве перед добавлением равно его ёмкости, то ёмкость массива увеличена вдвое
    // в конец массива добавлен элемент `itm`
    public abstract void append(T itm);

    // ------------------- запросы статусов -------------------

    public abstract int getGetItemStatus();
    public abstract int getInsertStatus();
    public abstract int getRemoveStatus();
}

public class DynArray<T> extends ADynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    private Class clazz;

    private int getItemStatus;
    private int insertStatus;
    private int removeStatus;

    private DynArray(Class clz) {
        clazz = clz;
        count = 0;
        makeArray(16);
    }

    @Override
    public T getItem(int idx) {
        if (idx < 0 || idx >= count) {
            getItemStatus = GET_ITEM_INDEX_OUT_OF_BOUND;
            return null;
        }
        getItemStatus = GET_ITEM_OK;
        return array[idx];
    }

    @Override
    public void insert(T itm, int idx) {
        if (idx < 0 || idx > count) {
            insertStatus = INSERT_INDEX_OUT_OF_BOUND;
            return;
        }
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        for (int i = count - 1; i >= idx; i--) {
            array[i + 1] = array[i];
        }
        array[idx] = itm;
        count++;
        insertStatus = INSERT_OK;
    }

    @Override
    public void remove(int idx) {
        if (idx < 0 || idx >= count) {
            removeStatus = REMOVE_INDEX_OUT_OF_BOUND;
            return;
        }
        for (int i = idx; i < count - 1; i++) {
            array[i] = array[i + 1];
        }
        count--;
        array[count] = null;
        if (count < (capacity + 2 - 1) / 2) {
            makeArray(Math.max(16, (int) (capacity / 1.5)));
        }
        removeStatus = REMOVE_OK;
    }

    @Override
    public void append(T itm) {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    private void makeArray(int new_capacity) {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (oldArray != null) {
            System.arraycopy(oldArray, 0, array, 0, count);
        }
        capacity = new_capacity;
    }

    @Override
    public int getGetItemStatus() {
        return getItemStatus;
    }

    @Override
    public int getInsertStatus() {
        return insertStatus;
    }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
    }
}
