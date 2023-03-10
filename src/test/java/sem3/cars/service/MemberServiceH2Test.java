package sem3.cars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Member;
import sem3.cars.repositories.MemberRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//Transactional (rollback)
//Includes what is necessary for JPA/Hibernate and ONLY that
//Uses an in-memory database (H2).
@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    public MemberRepository memberRepository;

    MemberService memberService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){  //Explain this
            memberRepository.save(new Member("m1", "test12", "m1@a.dk",  "bb", "Olsen", "xx vej 34", "Lyngby", "2800"));
            memberRepository.save(new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "xx vej 34", "Lyngby", "2800"));
            dataIsReady = true;
            memberService = new MemberService(memberRepository); //Real DB is mocked away with H2
        }
    }

    @Test
    void getMembersAdmin() {
        List<MemberResponse> members = memberService.getMembers(true);
        assertEquals(2,members.size());
        assertNotNull(members.get(0).getRanking());
    }

    @Test
    void getMembers() {
    }

    @Test
    void addMember() {
    }
}