package itewon.seon.repository;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.InsertTypeDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AbTypeRepository {
    List<AbTypeListDto> selectTypeList(SelectTypeListDto selectTypeListDto);
    int insertType(InsertTypeDto insertTypeDto);
    int deleteType(long typeSeq);
}
