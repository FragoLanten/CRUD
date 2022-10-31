import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        switch (args[0]) {
            case ("-c"):
                Date dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                if (args[2].equals("м")) {
                    Person newPerson = Person.createMale(args[1], dateFormat);
                    allPeople.add(newPerson);
                    System.out.println(allPeople.indexOf(newPerson));
                }
                else if (args[2].equals("ж")) {
                    Person newPerson = Person.createFemale(args[1],dateFormat);
                    allPeople.add(newPerson);
                    System.out.println(allPeople.indexOf(newPerson));
                }

                break;
            case ("-r"):
                Person chosenPerson = allPeople.get(Integer.parseInt(args[1]));

                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String outputDate = newFormat.format(chosenPerson.getBirthDate());

                String sex = "";
                if (chosenPerson.getSex().equals(Sex.MALE)) {
                    sex = "м";
                }
                else if (chosenPerson.getSex().equals(Sex.FEMALE)) {
                    sex = "ж";
                }
                System.out.println(chosenPerson.getName() + " " +
                        sex + " " +
                        outputDate);

                break;
            case ("-u"):

                int id = Integer.parseInt(args[1]);
                Person oldPerson = allPeople.get(id);
                if (oldPerson == null) {
                    throw new IllegalArgumentException();
                }
                oldPerson.setName(args[2]);

                if (args[3].equals("м")) {
                    oldPerson.setSex(Sex.MALE);
                }
                else if (args[3].equals("ж")) {
                    oldPerson.setSex(Sex.FEMALE);
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date birthdayDate = simpleDateFormat.parse(args[4]);

                oldPerson.setBirthDate(birthdayDate);

                allPeople.set(id,oldPerson);

                break;
            case  ("-d"):
                Person personToDelete = allPeople.get(Integer.parseInt(args[1]));
                personToDelete.setName(null);
                personToDelete.setSex(null);
                personToDelete.setBirthDate(null);
                break;
        }
    }
}
