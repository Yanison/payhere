package itewon.seon.repository;

import itewon.seon.dto.accountBook.SelectAccountBookDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountBookRepository {
    List<SelectAccountBookDto> selectMyAccountBook(long userSeq);
    long selectLastAccountBookOne();
    int createAccountBook(long userSeq);
    int updateAccountBook(UpdateAccountBookDto updateAccountBookDto);
    int deleteAccountBook(long abSeq);
}
