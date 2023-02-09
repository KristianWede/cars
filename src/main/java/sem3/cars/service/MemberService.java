package sem3.cars.service;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Member;
import sem3.cars.repositories.MemberRepository;

import javax.sound.midi.MetaMessage;
import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();

       /* List<MemberResponse> memberResponses = new ArrayList<>();



        for(Member m: members){
            MemberResponse mr = new MemberResponse(m,includeAll);
            memberResponses.add(mr);
        }

         */

        List<MemberResponse> memberResponses = members.stream().map(m->new MemberResponse(m,includeAll)).toList();

        return memberResponses;
    }

    public MemberResponse addMember(MemberRequest memberRequest){

        if(memberRepository.existsById(memberRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this ID already exist");
        }
        if(memberRepository.existsByEmail(memberRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
        }

        
        //Later you should add error checks --> Missing arguments, email taken etc.

        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, false);
    }

    public MemberResponse getMemberById(String username){
        return null;
    }


    public MemberResponse findMemberByUsername(String username) {
        Member m = memberRepository.findById(username).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member with this ID does not exist"));

        return new MemberResponse(m, true);
    }

    public Member editMember(MemberRequest body, String username) {

        Member m1 = memberRepository.findMemberByUsername(username);

        m1.setEmail(body.getEmail());
        m1.setPassword(body.getPassword());
        m1.setFirstName(body.getFirstName());
        m1.setLastName(body.getLastName());
        m1.setStreet(body.getStreet());
        m1.setCity(body.getCity());
        m1.setZip(body.getZip());



        return m1;

    }

    public void editMemberRanking(String username, int value) {

        Member m1 = memberRepository.findMemberByUsername(username);

        m1.setRanking(value);

        memberRepository.save(m1);

    }

    public void deleteMember(String username) {
        Member m1 = memberRepository.findMemberByUsername(username);
        memberRepository.delete(m1);
    }

    public ResponseEntity<Boolean> updateMember(MemberRequest body, String username){
        Member mtoUpdate = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        mtoUpdate.setFirstName(body.getFirstName());
        mtoUpdate.setLastName(body.getLastName());

        memberRepository.save(mtoUpdate);

        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }


}
