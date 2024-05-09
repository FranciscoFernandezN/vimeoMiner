package aiss.grupo6.vimeoMiner;

import aiss.grupo6.vimeoMiner.service.CaptionServiceTest;
import aiss.grupo6.vimeoMiner.service.ChannelServiceTest;
import aiss.grupo6.vimeoMiner.service.CommentServiceTest;
import aiss.grupo6.vimeoMiner.service.VideoServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ChannelServiceTest.class,
	VideoServiceTest.class,
	CommentServiceTest.class,
	CaptionServiceTest.class
})
public class VimeoMinerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
