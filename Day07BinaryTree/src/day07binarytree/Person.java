package day07binarytree;

public class Person {

        private String name;
        private String info;

        public Person(String name, String info) {
            this.name = name;
            this.info = info;
        }

        public String toString() {
            return name + " : " + info;
        }
}
