package model.components;

import java.lang.reflect.Constructor;

public class GenericTypeExploring {

    // Mục đích để control khi nào cần tạo Object loại T thì tạo
    public <T extends LoginPage> void login(Class<T> loginPageClass) {

        //<T extends LoginPage>: boundary generic type có giới hạn
        // <?>: why type - generic type ko có giới hạn
        Class<?>[] parameterTypes = new Class[]{};

        // Vì LoginPage ko có constructor có đối số, nên đưa vào 1 Array empty các Types của parameters.
        try {
            Constructor<T> constructor = loginPageClass.getConstructor(parameterTypes);
            T loginPageObj = constructor.newInstance();
            loginPageObj.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenericTypeExploring().login(InternalLoginPage.class);
        new GenericTypeExploring().login(ExternalLoginPage.class);
    }
}
