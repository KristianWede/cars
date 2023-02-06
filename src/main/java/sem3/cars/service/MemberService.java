package sem3.cars.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Member;
import sem3.cars.repositories.MemberRepository;

import java.util.ArrayList;
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





}
