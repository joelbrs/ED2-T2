package utils;

public abstract class HashUtils<TKey> {
    public abstract void rehashing(int newLength);

    public int getIndexByHashFunction(TKey key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    public int getIndexByHashFunction(TKey key, int length, int increment) {
        return Math.abs((key.hashCode() + increment) % length);
    }

    public double getLoadFactor(Object[] o, int length) {
        int sum = 0;
        for (var item: o) {
            if (item != null) {
                sum++;
            }
        }

        return (double) sum / length;
    }

    public void resizeToDouble(int length) {
        rehashing(length);
    }
}
