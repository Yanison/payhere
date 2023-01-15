package itewon.seon.service;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.InsertTypeDto;
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

    public List<SelectAccountBookDto> selectMyAccountBook(SelectAccountBookDto selectAccountBookDtoq){
        return accountBookRepository.selectMyAccountBook(selectAccountBookDtoq);
    }

    public  List<AbTypeListDto> SelectTypeList(SelectTypeListDto selectTypeListDto){
        return abTypeRepository.selectTypeList(selectTypeListDto);
    }
    @Transactional
    public long createAccountBook(long userSeq){
        accountBookRepository.createAccountBook(userSeq);
        return accountBookRepository.selectLastAccountBookOne();
    }
    public int updateAccountBook(UpdateAccountBookDto updateAccountBookDto){
        log.info("Service updateAccountBook getShKey = {}",updateAccountBookDto.getShKey());
        log.info("Service updateAccountBook getShValue = {}",updateAccountBookDto.getShValue());
        return accountBookRepository.updateAccountBook(updateAccountBookDto);
    }
    public int insertType(InsertTypeDto insertTypeDto){

        log.info("Service insertType typeName ::  {}", insertTypeDto.getTypeName());
        log.info("Service insertType useSeq ::  {}", insertTypeDto.getUserSeq());
        log.info("Service insertType abSeq ::  {}", insertTypeDto.getAbSeq());

        return abTypeRepository.insertType(insertTypeDto);
    }
    @Transactional
    public int deleteAccountBook(long abSeq){
        log.info("Service deleteAccountBook abSeq = {}",abSeq);
        return accountBookRepository.deleteAccountBook(abSeq);
    }
    @Transactional
    public int restoreAccountBook(long abSeq){
        log.info("Service restoreAccountBook abSeq = {}",abSeq);
        return accountBookRepository.restoreAccountBook(abSeq);
    }
    @Transactional
    public int permanentlyDeleteAccountBook(Long abSeq){
        log.info("Service permanentlyDeleteAccountBook abSeq = {}",abSeq);
        return accountBookRepository.permanentlyDeleteAccountBook(abSeq);
    }
    public int deleteType(long typeSeq){
        return abTypeRepository.deleteType(typeSeq);
    }
}
