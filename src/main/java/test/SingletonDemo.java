package test;

public class SingletonDemo {

    static Object object = new Object();

    private  static User user;
    public static User getUserInstance(String name) throws InterruptedException {
        if(user == null){
            synchronized (object){
                object=name;
                Thread.sleep(500);
                if(user == null){
                    user = new User();
                    user.setUserName(name);

                    return user;
                }


            }

        }
        return user;
    }

    static int tt = 0;

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<2000;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);
                        tt++;
//                        getUserInstance(System.currentTimeMillis()+"");
////                        System.out.println(Thread.currentThread().getName()+":"+getUserInstance(System.currentTimeMillis()+"").getUserName());
//                        System.out.println(getUserInstance(System.currentTimeMillis()+"").hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-------------");
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(tt);



    }


}
