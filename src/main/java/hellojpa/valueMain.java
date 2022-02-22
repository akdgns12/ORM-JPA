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


        //값타입 : 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야함
        int q = 10;
        int w = 10;

        System.out.println("q == w: " + (q == w));

        // == 비교는 참조값비교, 밑의 두 주소는 당연히 참조값이 다르다
        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("address1 == address2 = " + (address1 == address2));
        System.out.println("address1 equals address2 = " + (address1.equals(address2)));

        /*
            동일성 비교 : 인스턴스의 참조값을 비교, == 사용
            동등성 비교 : 인스턴스의 값을 비, equasl() 사용
            값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야함
            값 타입의 equals()메소드를 적절하게 재정(주로 모든 필드 사용)
        */
    }
}
