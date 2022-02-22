package hellojpa;

public class valueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = a; //기본 타입은 값을 복사

        b = 20;

        Integer c = new Integer(10);
        Integer d = c; //객체 타입은 참조를 전

//        c.setValue()
        // 이런식으로 자바 기본값 타입은 절대 공유될 수 없다(서로 다른 저장소를 가지고 있다), 기본 타입은 항상 값을 복사함
        // But, Integer같은 래퍼클래스나 String같은 특수한 클래스는 공유가능한 객체이지만 변경은 X(값을 복사하는게 아닌 주소를
        // 참조하기 때문에!)
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
