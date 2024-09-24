class Person {
    private String name;
    private int age;
    private String address;
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}




import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // Original object from the database
        Person originalPerson = new Person("John Doe", 30, "123 Elm Street");

        // Updated object with some changes
        Person updatedPerson = new Person("John Doe", 32, "123 Elm Street");

        // Compare the objects using reflection
        compareObjects(originalPerson, updatedPerson);
    }

    public static void compareObjects(Object oldObj, Object newObj) throws IllegalAccessException {
        Class<?> clazz = oldObj.getClass();  // Get the class type

        // Get all fields of the class
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);  // Make private fields accessible

            // Get the value of the field from both objects
            Object oldValue = field.get(oldObj);
            Object newValue = field.get(newObj);

            // Compare old and new values
            if (oldValue != null && !oldValue.equals(newValue)) {
                System.out.println("Field '" + field.getName() + "' updated from '" + oldValue + "' to '" + newValue + "'");
            }
        }
    }
}


// -- Updated 


  import java.lang.reflect.Field;
import java.util.*;

 class ReflectionComparison {

    public static List<Map<String, Object>> getUpdatedFields(Object oldObj, Object newObj) throws IllegalAccessException {
        List<Map<String, Object>> updatedFields = new ArrayList<>();

        Class<?> clazz = oldObj.getClass();
        Field[] fields = clazz.getDeclaredFields(); // Get all fields of the class

        for (Field field : fields) {
            field.setAccessible(true); // Access private fields

            Object oldValue = field.get(oldObj);
            Object newValue = field.get(newObj);

            // Compare old and new values
            if (oldValue != null && !oldValue.equals(newValue)) {
                // Create a map to store the field name and new value
                Map<String, Object> updatedField = new HashMap<>();
                updatedField.put(field.getName(), newValue);

                // Add the updated field to the list
                updatedFields.add(updatedField);
            }
        }

        return updatedFields;
    }

    public static void main(String[] args) throws IllegalAccessException {
        // Example usage
        Person oldPerson = new Person(1L, "John", 25);
        Person newPerson = new Person(1L, "John", 30); // Updated age

        List<Map<String, Object>> updates = getUpdatedFields(oldPerson, newPerson);

        // Print the updated fields
        for (Map<String, Object> update : updates) {
            System.out.println(update);
        }
    }
}




