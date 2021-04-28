public class Main {
    public static void main(String[] args) {
        UserMapper.createTable();
        UserMapper.addUser(new User(1,"2",3));
        UserMapper.findById(1L);
        UserMapper.findById(1L);
        UserMapper.deleteById(121L);
//        System.out.println(user);
//        UserMapper.deleteById(22L);
    }
}
