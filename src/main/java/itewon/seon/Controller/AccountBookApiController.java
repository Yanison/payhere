package itewon.seon.Controller;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.InsertTypeDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import itewon.seon.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                if(updateAccountBookDto.getShKey() != "type"){
                    log.info("updateAccountBook key != type :: {}",updateAccountBookDto.getShKey());
                    int result = accountBookService.updateAccountBook(updateAccountBookDto);
                    return result;


                }

                log.info("updateAccountBook key = type ::  {}",updateAccountBookDto.getShKey());
                int result = accountBookService.insertType(new InsertTypeDto(
                        updateAccountBookDto.getUserSeq(),
                        updateAccountBookDto.getShValue()
                ));
                return result;
            }catch (Exception e){
                e.getStackTrace();
                return 0;
            }
        }

    @PostMapping("/insertType")
    public int insertType(InsertTypeDto insertTypeDto){
        try{
            System.out.println("this type is "+insertTypeDto.getShValue().getClass());

            log.info("insertType shValue ::  {}", insertTypeDto.getShValue());
            log.info("insertType shValue ::  {}", insertTypeDto.getShValue());
            int result = accountBookService.insertType(insertTypeDto);

            return result;
        }catch (Exception e){
            e.getStackTrace();
            return 0;
        }
    }

        @PostMapping("/delete")
        public int deleteAccountBook(@RequestParam long abSeq){
            log.info("deleteAccountBook abSeq = {}",abSeq);
            try{
                int result = accountBookService.deleteAccountBook(abSeq);
                return result;
            }catch (Exception e){
                e.getStackTrace();
                return 0;
            }
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
