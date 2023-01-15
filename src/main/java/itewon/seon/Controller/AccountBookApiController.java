package itewon.seon.Controller;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.InsertTypeDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import itewon.seon.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accountbook")
@RequiredArgsConstructor
public class AccountBookApiController{
    private final AccountBookService accountBookService;

        @PostMapping("/create")
        public long createAccountBook(@RequestParam long userSeq){
            System.out.println(userSeq);
            long abSeq = accountBookService.createAccountBook(userSeq);
            log.info("createAccountBook abSeq = {}",abSeq);
            return abSeq;
        }

        @PostMapping("/update")
        public int updateAccountBook(UpdateAccountBookDto updateAccountBookDto){
            try{
                System.out.println("this type is "+updateAccountBookDto.getShKey().getClass());
                log.info("updateAccountBook key != type :: {}",updateAccountBookDto.getShKey());
                return accountBookService.updateAccountBook(updateAccountBookDto);
            }catch (Exception e){
                e.getStackTrace();
                return 0;
            }
        }

    @PostMapping("/insertType")
    public int insertType(InsertTypeDto insertTypeDto){
        try{
            System.out.println("this type is "+insertTypeDto.getTypeName().getClass());

            log.info("insertType typeName ::  {}", insertTypeDto.getTypeName());
            log.info("insertType useSeq ::  {}", insertTypeDto.getUserSeq());
            log.info("insertType abSeq ::  {}", insertTypeDto.getAbSeq());
            int result = accountBookService.insertType(insertTypeDto);

            return result;
        }catch (Exception e){
            e.getStackTrace();
            return 0;
        }
    }

    @PostMapping("/delete")
    public HashMap<String, String> deleteAccountBook(@RequestParam(value="checkList") String data){
        log.info("deleteAccountBook checkList = {}",data);
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            JSONParser parser = new JSONParser(data);
            for(Object seq : parser.parseArray()){
                long abSeq = Long.parseLong((String) seq) ;
                System.out.println("deleting this :: "  + abSeq);
                accountBookService.deleteAccountBook(abSeq);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("errorMessage",e.getMessage());
        }
        response.put("result",data);
        return response;
    }

    @PostMapping("/restore")
    public HashMap<String, String> restoreAccountBook(@RequestParam(value="checkList") String data) {
        log.info("restoreAccountBook checkList = {}",data);
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            JSONParser parser = new JSONParser(data);
            for(Object seq : parser.parseArray()){
                long abSeq = Long.parseLong((String) seq) ;
                System.out.println("restore this :: "  + abSeq);
                accountBookService.restoreAccountBook(abSeq);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("errorMessage",e.getMessage());
        }
        response.put("result",data);
        return response;
    }

    @PostMapping("/permanentlyDelete")
    public HashMap<String, String> permanentlyDelete(@RequestParam(value="checkList") String data) {
        log.info("permanentlyDelete checkList = {}",data);
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            JSONParser parser = new JSONParser(data);
            for(Object seq : parser.parseArray()){
                long abSeq = Long.parseLong((String) seq) ;
                System.out.println("restore this :: "  + abSeq);
                accountBookService.permanentlyDeleteAccountBook(abSeq);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("errorMessage",e.getMessage());
        }
        response.put("result",data);
        return response;
    }


    @PostMapping("/deleteType")
    public int deleteType(@RequestParam long typeSeq){
        log.info("deleteType typeSeq = {}",typeSeq);
        try{
            int result = accountBookService.deleteType(typeSeq);
            return result;
        }catch (Exception e){
            e.getStackTrace();
            return 0;
        }
    }

    @GetMapping("/selectTypeList")
    public HashMap<String,Object> selectTypeList(SelectTypeListDto selectTypeListDto){
        HashMap<String,Object> response = new HashMap<String,Object>();
        List<AbTypeListDto> selectTypeList = accountBookService.SelectTypeList(selectTypeListDto);
        response.put("list",selectTypeList);
        return response;
    }
}
