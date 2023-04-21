//package ch.ilv5.aniclo;
//
//import ch.ilv5.aniclo.model.Places;
//import ch.ilv5.aniclo.repository.PlacesRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest()
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//class DBTests {
//
//    @Autowired
//    private PlacesRepository placesRepository;
//
//    @Test
//    void insertPlace() {
//        Places objCar = this.placesRepository.save(new Places(1L, 4106, "Therwil", "Ettingerstrasse", 4));
//        Assertions.assertNotNull(objCar.getId());
//    }
//}
