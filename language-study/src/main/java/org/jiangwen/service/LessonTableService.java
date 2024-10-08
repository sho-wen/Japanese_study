package org.jiangwen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jiangwen.entity.LessonTable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author name：JiangWen
 * @since 2021-11-20
 */
public interface LessonTableService extends IService<LessonTable> {

    int hasLessonNumber(long bookId, long lessonNumber);

    List<Long> hasChildSentence(Long[] lessonIds);
}
