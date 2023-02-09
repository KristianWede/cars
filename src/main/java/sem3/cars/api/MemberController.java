package sem3.cars.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Member;
import sem3.cars.repositories.MemberRepository;
import sem3.cars.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("api/members")
class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //Security? ADMIN ONLY
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false);
    }

    //Security? ADMIN and USER ??
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findMemberByUsername(username);
    }

    //Security? ANONYMOUS
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Security? MEMBER
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        memberService.updateMember(body,username);
        return ResponseEntity.ok(true);
    }

    //Security? ADMIN
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {

        memberService.editMemberRanking(username,value);

    }

    // Security? ADMIN
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {

        memberService.deleteMember(username);

    }




    /*

    @Autowired  //Deliberately added via Autowired, remove this endpoint when you know why it's bad
    MemberRepository memberRepository;
    @GetMapping("/bad")
    public List<Member> getMembersBad(){
        return memberRepository.findAll();
    }

     */


}

