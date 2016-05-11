/*
 *   Copyright (C) 2016  MohiPE
 *   
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *   
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *   
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.orgz;

import java.util.LinkedHashMap;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

/**
 * @author MohiPE
 * @since 2016-05-11 16:05
 * 
 * 다양한 방법의 Config 활용법들을 추가하실 분들 환영합니다. 주석은 최대한 많이 넣어주세요.
 */
public class ConfigExample extends PluginBase {
	
	private ConfigSection score; //사람들의 점수를 기록한 score.json를 역직렬화해서 저장합니다.
	private ConfigSection students; //사람들의 다양한 정보를 기록한 students.json를 역직렬화해서 저장합니다.

	/**
	 * 플러그인이 로드될 때 실행되는 메서드입니다.
	 */
	@Override
	public void onLoad() {

	}

	/**
	 * 플러그인이 활성화 될 때 실행되는 메서드입니다.
	 */
	@Override
	public void onEnable() {
		this.getDataFolder().mkdirs(); //ConfigExample 이라는 폴더를 plugins 하위에 생성합니다.
		this.loadAll();
		//TODO SaveTask 넣기.
	}
	
	@Override
	public void onDisable() {
		this.saveAll();
	}

	/**
	 * Config 클래스를 이용해 다양한 데이터들을 불러올 메서드입니다.
	 */
	public void loadAll() {
		this.saveDefaultConfig(); // PluginBase에는 이렇게 config.yml 파일을 알아서 만들어주는 넘이 있습니다.
		this.loadScore();
	}
	
	/**
	 * score.json 파일을 score변수에 deserialize하여  넣습니다.
	 */
	@SuppressWarnings("serial")
	public void loadScore() {
		this.score = new Config(this.getDataFolder() + "/score.json", Config.JSON,
				new ConfigSection(new LinkedHashMap<String, Object>() {
					{
						// 만약 파일에 이미 다른 것들이 쓰여져 있다면, 아래는 무시될겁니다.

						put("mohi", 100); // key에 문자열 "mohi"를, value에 정수 100을
											// 넣습니다.
						put("hm", 0);
						put("maru", 79);
						put("onebone", 100);
						put("debe", 50);
						
						/* {
						 *  "mohi": 100,
						 *  "maru": 79,
						 *  "onebone": 100,
						 *  "debe": 50
						 * }
						 */
					}
				})).getSections(); // 예전엔 LinkedHashMap을 사용했었는데, Deprecated되었기
									// 때문에 앞으로는 ConfigSection 써줍시다.
	}
	/**
	 * students.json 파일을 students변수에 deserialize하여 넣습니다.
	 */
	public void loadStudents() {
		
	}
	/**
	 * 모두 저장합니다.
	 */
	public void saveAll() {
		this.saveScore();
		this.getConfig().save();
	}
	/**
	 * score을 score.json에 JSON으로 저장합니다.
	 * 
	 * @param boolean
	 */
	public void saveScore(boolean async) {
		Config score = new Config(this.getDataFolder() + "/score.json", Config.JSON);
		score.setAll(this.score);
		score.save(async); // async가 true면 저장이 비동기로 실행됩니다.
	}

	/**
	 * score을 score.json에 JSON으로 저장합니다.
	 */
	public void saveScore() {
		Config score = new Config(this.getDataFolder() + "/score.json", Config.JSON);
		score.setAll(this.score);
		score.save();
	}
}
