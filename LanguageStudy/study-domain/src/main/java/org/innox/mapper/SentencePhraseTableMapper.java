package org.innox.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.innox.pojo.SentencePhraseTable;
import org.innox.pojo.SentencePhraseTableExample;
import org.innox.pojo.SentencePhraseTableKey;

public interface SentencePhraseTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    long countByExample(SentencePhraseTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int deleteByExample(SentencePhraseTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int deleteByPrimaryKey(SentencePhraseTableKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int insert(SentencePhraseTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int insertSelective(SentencePhraseTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    List<SentencePhraseTable> selectByExample(SentencePhraseTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    SentencePhraseTable selectByPrimaryKey(SentencePhraseTableKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int updateByExampleSelective(@Param("record") SentencePhraseTable record, @Param("example") SentencePhraseTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int updateByExample(@Param("record") SentencePhraseTable record, @Param("example") SentencePhraseTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int updateByPrimaryKeySelective(SentencePhraseTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sentence_phrase_table
     *
     * @mbg.generated Mon Apr 26 20:05:47 JST 2021
     */
    int updateByPrimaryKey(SentencePhraseTable record);
}