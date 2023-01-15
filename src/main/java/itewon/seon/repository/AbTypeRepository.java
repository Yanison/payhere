package itewon.seon.repository;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AbTypeRepository {
    List<AbTypeListDto> selectTypeList(SelectTypeListDto selectTypeListDto);
    int insertType(UpdateAccountBookDto updateAccountBookDto);
    int deleteType(long typeSeq);
}
