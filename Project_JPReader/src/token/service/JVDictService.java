package token.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import token.entity.JVDict;

@Transactional
@Component
public class JVDictService {
	@Autowired
	SessionFactory factory;

	public String getMeaning(String word) {
		System.out.println("Vao duoc day roi");
		String hql = "FROM JVDict WHERE kanji=:word";
		String meaning="";
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		query.setParameter("word", word);
		JVDict x = (JVDict) query.uniqueResult();
		if (x == null) {
			String hql2 = "FROM JVDict WHERE hikaWord=:word";
			query = session.createQuery(hql2);
			query.setParameter("word", word);
			List<JVDict> y = query.list();
			for (JVDict jvDict : y) {
				if (jvDict.getKanji()=="") {
					meaning = jvDict.getMeaning();
					System.out.println("Get Hika from DB");
					break;
				}
			}
		} else {
			System.out.println("Get kanji from DB");
			meaning = x.getMeaning();
		}
		if (meaning=="") {
			meaning = ExtractTextService.extractMeaning(word);
		}
		return meaning;
	}
}
