public class Day03CustomHashMap {

    public static void main(String[] args) {
	// write your code here

        CustomHashMapStringString hashMap = new CustomHashMapStringString();
        hashMap.putValue("testKey", "testValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.putValue("newKey", "newValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.putValue("againKey", "againValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.putValue("tripKey", "tripValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.putValue("tripKey", "changeValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.putValue("anotherKey", "anotherValue");
        System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.deleteByKey("anotherKey");
        hashMap.deleteByKey("againKey");
        //System.out.printf("%nSize: %d %n %s", hashMap.getSize(), hashMap.toString());
        hashMap.printDebug();
    }
}
