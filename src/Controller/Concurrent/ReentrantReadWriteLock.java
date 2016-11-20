package Controller.Concurrent;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * A reentrant read/write lock that allows: 
 * 1) Multiple readers (when there is no writer).
 * 2) One writer (when nobody else is writing or reading). 
 * 3) A writer is allowed to acquire a read lock while holding the write lock. 
 * The assignment is based on the assignment of Prof. Rollins (original author).
 */
public class ReentrantReadWriteLock
{

	// TODO: Add instance variables : you need to keep track of the read lock holders and the write lock holders.
	// We should be able to find the number of read locks and the number of write locks 
	// a thread with the given threadId is holding 
	Map<Long,Integer> readerMap;
	Map<Long,Integer> writerMap;
	Logger logger= LogManager.getLogger();
	/**
	 * Constructor for ReentrantReadWriteLock
	 */
	public ReentrantReadWriteLock()
	{
		// FILL IN CODE
		readerMap=new HashMap<>();
		writerMap=new HashMap<>();

	}

	/**
	 * Returns true if the current thread holds a read lock.
	 * 
	 * @return
	 */
	public synchronized boolean isReadLockHeldByCurrentThread()
	{

		// FILL IN CODE
		long currentThreadId=Thread.currentThread().getId();
		try
		{
			if(readerMap.get(currentThreadId)>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(NullPointerException e)
		{
			return false;
		}

	}

	/**
	 * Returns true if the current thread holds a write lock.
	 * 
	 * @return
	 */
	public synchronized boolean isWriteLockHeldByCurrentThread()
	{
		// FILL IN CODE
		long currentThreadId=Thread.currentThread().getId();
		try
		{
			if(writerMap.get(currentThreadId)>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (NullPointerException e)
		{
			return false;
		}

	}

	/**
	 * Non-blocking method that tries to acquire the read lock. Returns true
	 * if successful.
	 * 
	 * @return
	 */
	public synchronized boolean tryAcquiringReadLock()
	{
		// FILL IN CODE
		boolean canLock=false;
		long currentThreadId=Thread.currentThread().getId();
		int writerCount=0;

		if(writerMap.isEmpty())
		{
			canLock=true;
		}
		else if(!writerMap.isEmpty())
		{
			if(isWriteLockHeldByCurrentThread())
				canLock=true;
			else
				canLock=false;
		}
		else
			canLock=false;
		if(canLock)
		{
			if(!readerMap.containsKey(currentThreadId))
			{
				readerMap.put(currentThreadId,1);
			}
			else
			{
				int readerCount=readerMap.get(currentThreadId)+1;
				readerMap.put(currentThreadId,readerCount);
			}
			return true;
		}
		else
			return false;

	}

	/**
	 * Non-blocking method that tries to acquire the write lock. Returns true
	 * if successful.
	 * 
	 * @return
	 */
	public synchronized boolean tryAcquiringWriteLock()
	{
		// FILL IN CODE
		int writerCount=0;
		int readerCount=0;
		boolean canLock=false;
		long currentThreadId=Thread.currentThread().getId();
		if(writerMap.isEmpty()&&readerMap.isEmpty())
		{
			canLock=true;
		}
		else if(!writerMap.isEmpty())
		{
			if(isWriteLockHeldByCurrentThread())
				canLock=true;
			else
				canLock=false;
		}
		else
			canLock=false;
		if(canLock)
		{
			if(!writerMap.containsKey(currentThreadId))
			{
				writerMap.put(currentThreadId,1);
			}
			else
			{
				int writers=writerMap.get(currentThreadId)+1;
				writerMap.put(currentThreadId,writers);
			}
			return true;
		}
		else
			return false;

	}

	/**
	 * Blocking method - calls tryAcquiringReadLock and returns only when the read lock has been
	 * acquired, otherwise waits.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void lockRead()
	{
		// FILL IN CODE
		boolean isGottenReadLock=tryAcquiringReadLock();
		while(!isGottenReadLock)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Releases the read lock held by the current thread. 
	 */
	public synchronized void unlockRead()
	{
		// FILL IN CODE
		long currentThreadId=Thread.currentThread().getId();
		int readerCount=readerMap.get(currentThreadId)-1;
		if(readerCount==0)
			readerMap.remove(currentThreadId);
		else
			readerMap.put(currentThreadId,readerCount);
		notify();

	}

	/**
	 * Blocking method that calls tryAcquiringWriteLock and returns only when the write lock has been
	 * acquired, otherwise waits.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void lockWrite()
	{
		// FILL IN CODE
		// FILL IN CODE
		boolean isGottenWriteLock=tryAcquiringWriteLock();
		while(!isGottenWriteLock)
		{
			try
			{
				//logger.fatal(Thread.currentThread().getId()+"write wait!");
				wait();

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * Releases the write lock held by the current thread. 
	 */

	public synchronized void unlockWrite()
	{
		// FILL IN CODE

		long currentThreadId=Thread.currentThread().getId();
		int writerCount=writerMap.get(currentThreadId)-1;
		if(writerCount==0)
			writerMap.remove(currentThreadId);
		else
			writerMap.put(currentThreadId,writerCount);
		notify();
	}
}
