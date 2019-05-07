package com.langmy.terminal.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * lzy
 */
@Entity
@Table(name = "driveway", catalog = "cloud")
public class Driveway extends GenIdEntityImpl {

	private static final long serialVersionUID = -8187246240346507688L;

	/**
	 * 车道名称
	 */
	private String name;
	/**
	 * 车道对应卡口
	 */
	private Bayonet bayonet;
	/**
	 * 自动放行规则
	 */
	private String releaseRule;

	/**
	 * 车道方向
	 */
	private int direction;

	/**
	 * 终端机编号
	 */
	private Device terminal;

	/**
	 * 删除标志位
	 */
	private boolean delFlag;
	
	
	/**
	 * 车道对应多个摄像头
	 */
	private List<Camera> camera = Lists.newArrayList();
	
	public Driveway() {
		super();
	}

	/**
	 * @param name
	 *            车道名称
	 * @param bayonet
	 *            车道对应卡口
	 * @param releaseRule
	 *            自动放行规则
	 */
	public Driveway(String name, Bayonet bayonet, String releaseRule) {
		super();
		this.name = name;
		this.bayonet = bayonet;
		this.releaseRule = releaseRule;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bayonet_id")
	public Bayonet getBayonet() {
		return bayonet;
	}

	public void setBayonet(Bayonet bayonet) {
		this.bayonet = bayonet;
	}

	@Column(name = "release_rule", length = 50)
	public String getReleaseRule() {
		return releaseRule;
	}

	public void setReleaseRule(String releaseRule) {
		this.releaseRule = releaseRule;
	}

	@Column(name = "direction")
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	public Device getTerminal() {
		return terminal;
	}

	public void setTerminal(Device terminal) {
		this.terminal = terminal;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "driveway")
	public List<Camera> getCamera() {
		return camera;
	}

	public void setCamera(List<Camera> camera) {
		this.camera = camera;
	}

}
