package itewon.seon.service;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import itewon.seon.dto.accountBook.SelectAccountBookDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import itewon.seon.repository.AbTypeRepository;
import itewon.seon.repository.AccountBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBookService {
    private final AccountBookRepository accountBookRepository;
    private final AbTypeRepository abTypeRepository;

    public List<SelectAccountBookDto> selectMyAccountBook(long userSeq){
        return accountBookRepository.selectMyAccountBook(userSeq);
    }

    public  List<AbTypeListDto> SelectTypeList(SelectTypeListDto selectTypeListDto){
        return abTypeRepository.selectTypeList(selectTypeListDto);
    }
    @Transactional
    public long createAccountBook(long userSeq){
        accountBookRepository.createAccountBook(userSeq);
        return accountBookRepository.selectLastAccountBookOne();
    };
    public int updateAccountBook(UpdateAccountBookDto updateAccountBookDto){
        return accountBookRepository.updateAccountBook(updateAccountBookDto);
    };
    public int deleteAccountBook(long abSeq){
        log.info("Service deleteAccountBook abSeq = {}",abSeq);
        return accountBookRepository.deleteAccountBook(abSeq);
    };
}
